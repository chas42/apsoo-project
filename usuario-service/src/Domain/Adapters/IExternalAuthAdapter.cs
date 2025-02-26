using DataBase;
using Microsoft.AspNetCore.Authentication;

namespace Domain.Adapters
{
    public interface IExternalAuthAdapter
    {
        UserAuthorized Adapt(AuthenticateResult result);
    }
}
