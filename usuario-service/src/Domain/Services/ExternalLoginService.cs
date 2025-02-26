using DataBase;
using Domain.Adapters;
using Domain.Models;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using System.Security.Claims;
namespace ProjetoAPSOO.Services
{
    public class ExternalLoginService : IExternalLoginService
    {
        private readonly UserManager<UserAuthorized> _userManager;
        private readonly SignInManager<UserAuthorized> _signInManager;
        private readonly IExternalAuthAdapter _authAdapter;

        public ExternalLoginService(UserManager<UserAuthorized> userManager, SignInManager<UserAuthorized> signInManager, IExternalAuthAdapter authAdapter)
        {
            _userManager = userManager;
            _signInManager = signInManager;
            _authAdapter = authAdapter;
        }

        public async Task<UserDto> RealizarLoginExterno(HttpContext httpContext)
        {
            var authenticateResult = await httpContext.AuthenticateAsync();

            if (!authenticateResult.Succeeded || authenticateResult.Principal == null)
            {
                return null;
            }

            var userEmail = authenticateResult.Principal.FindFirst(ClaimTypes.Email)?.Value;
            var providerUserId = authenticateResult.Principal.FindFirst(ClaimTypes.NameIdentifier)?.Value;

            if (string.IsNullOrEmpty(userEmail))
            {
                return null;
            }

            var user = await _userManager.FindByEmailAsync(userEmail);

            if (user == null)
            {
                user = _authAdapter.Adapt(authenticateResult);

                var createUserResult = await _userManager.CreateAsync(user);
                if (!createUserResult.Succeeded)
                {
                    return null;
                }

                var loginInfo = new UserLoginInfo("Google", providerUserId, "Google");
                await _userManager.AddLoginAsync(user, loginInfo);
            }

            await _signInManager.SignInAsync(user, isPersistent: false);

            return new UserDto(user.Id, user.Email);
        }
    }
}
