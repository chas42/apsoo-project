using Microsoft.AspNetCore.Identity;

namespace DataBase
{
    public class UserAuthorized : IdentityUser<int>
    {
        public string? Name { get; set; }
        public DateOnly? Birthday { get; set; }
    }

}
