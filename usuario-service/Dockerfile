# Base Container
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS base
WORKDIR /app

#Build Container
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build

#Disponibilizar recursos do src
WORKDIR /src/temp
COPY src ./src
# COPY ./nuget.config . -> Não está funfando porque oa arquivo está ausente.

#Muda diretório de trabalho para src copiado
WORKDIR /src/temp/src

#Restore/Build Project
RUN dotnet restore "Api/Api.csproj"
RUN dotnet build "Api/Api.csproj" -c Release -o /app/build

#Publish/Release Container
FROM build AS publish
RUN dotnet publish "Api/Api.csproj" -c Release -o /app/publish

ENV ASPNETCORE_URLS=http://+:8080

#Release Container
FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "Api.dll"]