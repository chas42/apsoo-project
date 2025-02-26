using DataBase;
using Domain.Models;
using Microsoft.AspNetCore.Http;

namespace ProjetoAPSOO.Services
{
    public interface IExternalLoginService
    {
        public Task<UserDto> RealizarLoginExterno(HttpContext httpContext);

    }
}
