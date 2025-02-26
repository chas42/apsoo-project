namespace Domain.Models
{
    public class UserDto
    {
        public UserDto(int id,string email, string name, DateOnly birthday)
        {
            Id = id;
            Email = email;
            Name = name;
            Birthday = birthday;
        }  
        public UserDto(int id,string email)
        {
            Id = id;
            Email = email;
        }

        public UserDto() { }

        public int Id { get; set; }
        public string UserName { get; set; }
        public string Email { get; set; }
        public string Name { get; set; }
        public DateOnly Birthday { get; set; }
    
    }

}
