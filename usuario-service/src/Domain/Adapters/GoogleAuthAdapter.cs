using DataBase;
using Domain.Models;
using Microsoft.AspNetCore.Authentication;
using System.Security.Claims;

namespace Domain.Adapters
{
    public class GoogleAuthAdapter : IExternalAuthAdapter
    {
        public UserAuthorized Adapt(AuthenticateResult result)
        {
            return new UserAuthorized
            {
                UserName = result.Principal.FindFirst(ClaimTypes.NameIdentifier)?.Value,
                Name = result.Principal.FindFirst(ClaimTypes.Name)?.Value,
                Email = result.Principal.FindFirst(ClaimTypes.Email)?.Value
            };
        }
    }
}
