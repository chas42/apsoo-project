using DataBase;
using Domain.Models;
using Microsoft.AspNetCore.Identity;
using ProjetoAPSOO.Services;

public class UserManagerService : IUserManagerService
{
    private readonly UserManager<UserAuthorized> _userManager;

    public UserManagerService(UserManager<UserAuthorized> userManager)
    {
        _userManager = userManager;
    }

    public async Task<UserDto?> GetUserByIdAsync(string id)
    {
        var user = await _userManager.FindByIdAsync(id);
        if(user == null)
        {
            return null;
        }

        return new UserDto(user.Id, user.Email);
    }

    public async Task<List<UserDto>> GetAllUsers()
    {
        var users = _userManager.Users?.ToList();

        return users.Select(user => new UserDto()
        {
            Id = user.Id,
            Name = user.Name,
            Email = user.Email ,
            Birthday = user.Birthday ?? DateOnly.MinValue
        }).ToList();
    }

    public async Task<(bool isSuccess, string message)> UpdateUserAsync(string id, UserDto userUpdated)
    {
        var user = await _userManager.FindByIdAsync(id);
        if (user == null) return (false, "Usuário não encontrado.");

        await _userManager.UpdateAsync(user);
        return (true, null);
    }

    public async Task<(bool isSuccess, string message)> DeleteUserAsync(string id)
    {
        var user = await _userManager.FindByIdAsync(id);
        if (user == null) return (false, "Usuário não encontrado.");

        await _userManager.DeleteAsync(user);
        return (true, null);
    }
}
