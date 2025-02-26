using DataBase;
using Domain.Models;
using Microsoft.AspNetCore.Http;

namespace ProjetoAPSOO.Services
{
    public interface IUserManagerService
    {
        public Task<UserDto?> GetUserByIdAsync(string id);
        public Task<List<UserDto>> GetAllUsers();
        public Task<(bool isSuccess, string message)> UpdateUserAsync(string id, UserDto userUpdated);
        public Task<(bool isSuccess, string message)> DeleteUserAsync(string id);


    }
}
