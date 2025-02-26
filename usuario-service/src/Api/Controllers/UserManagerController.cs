using Domain.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using ProjetoAPSOO.Services;

namespace ProjetoAPSOO.Controllers
{

    [ApiController]
    public class UserManagerController : Controller
    {
        private readonly IUserManagerService _userService;
        private readonly ILogger<UserManagerController> _logger;

        public UserManagerController(IUserManagerService userService, ILogger<UserManagerController> logger)
        {
            _userService = userService;
            _logger = logger;
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetUserById(string id)
        {
            try
            {
                var userDTO = await _userService.GetUserByIdAsync(id);

                if (userDTO == null) return NotFound(new { message = "Usuário não encontrado." });

                return Ok(userDTO);

            }catch (Exception ex)
            {
                var message = $"An error occur executing {nameof(GetUserById)}: {id}, Exception message: {ex.Message}";
                _logger.LogError(message);
                return StatusCode(500 , message);
            }
        }

        [HttpGet("")]
        public async Task<IActionResult> GetAllUsers()
        {
            try
            {
                var users = await _userService.GetAllUsers();

                return Ok(users);
            }
            catch (Exception ex)
            {
                var message = $"An error occur executing {nameof(GetAllUsers)}, Exception message: {ex.Message}";
                _logger.LogError(message);
                return StatusCode(500, message);
            }
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> UpdateUser(string id, [FromBody] UserDto request)
        {
            try
            {
                var result = await _userService.UpdateUserAsync(id, request);
                
                if (!result.isSuccess) return BadRequest(result.message);

                return Ok(new { message = "Usuário atualizado com sucesso!" });

            }
            catch (Exception ex)
            {
                var message = $"An error occur executing {nameof(UpdateUser)}, Exception message: {ex.Message}";
                _logger.LogError(message);
                return StatusCode(500, message);
            }
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteUser(string id)
        {
            try
            {
                var result = await _userService.DeleteUserAsync(id);
                if (!result.isSuccess) return BadRequest(result.message);

                return Ok(new { message = "Usuário deletado com sucesso!" });
            }
            catch (Exception ex)
            {
                var message = $"An error occur executing {nameof(DeleteUser)}, Exception message: {ex.Message}";
                _logger.LogError(message);
                return StatusCode(500, message);
            }
        }
    }
}
