package br.com.apsoo.property.service;

import br.com.apsoo.property.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    UserDTO getById(Long id);

}
