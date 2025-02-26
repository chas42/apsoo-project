using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace DataBase.Context
{
    public class AppDbContext : IdentityDbContext<UserAuthorized, Profile, int>
    {
        private string connectionString = "Data Source=(localdb)\\ProjectModels;Initial Catalog=\"MonthlyPropertyRental \";Integrated Security=True;Connect Timeout=30;Encrypt=False;Trust Server Certificate=False;Application Intent=ReadWrite;Multi Subnet Failover=False";

        public AppDbContext()
        {
        }
        public AppDbContext(DbContextOptions options) : base(options)
        {

        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (optionsBuilder.IsConfigured)
            {
                return;
            }
            optionsBuilder
                .UseSqlServer(connectionString)
                .UseLazyLoadingProxies();
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<UserAuthorized>()
                .Property(p => p.Birthday)
                .HasColumnName("Birthday");

            modelBuilder.Entity<UserAuthorized>()
                .Property(p => p.Name)
                .HasColumnName("Name");
        }
    }

}
