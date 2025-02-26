using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Google;
using Microsoft.AspNetCore.Mvc;
using ProjetoAPSOO.Services;

namespace ProjetoAPSOO.Controllers
{
    [ApiController]
    public class ExternalLoginController : Controller
    {
        private readonly IExternalLoginService _authService;

        public ExternalLoginController(IExternalLoginService authService)
        {
            _authService = authService;
        }

        [HttpGet("login/google")]
        public IActionResult LoginWithGoogle()
        {
            var redirectUrl = Url.Action(nameof(GoogleCallback));
            return Challenge(new AuthenticationProperties { RedirectUri = redirectUrl }, GoogleDefaults.AuthenticationScheme);
        }

        [HttpGet("signin-google")]
        public async Task<IActionResult> GoogleCallback()
        {
            var user = await _authService.RealizarLoginExterno(HttpContext);

            if (user == null)
            {
                return Unauthorized(new { Message = "Falha ao autenticar com o Google." });
            }

            return Ok(new
            {
                Message = "Autenticação bem-sucedida com o Google.",
                User = user
            });
        }
    }
}
