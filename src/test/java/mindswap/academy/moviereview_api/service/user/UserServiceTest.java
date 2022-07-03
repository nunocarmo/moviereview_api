package mindswap.academy.moviereview_api.service.user;

import mindswap.academy.moviereview_api.command.user.UserDto;
import mindswap.academy.moviereview_api.converter.user.UserConverter;
import mindswap.academy.moviereview_api.persistence.repository.user.IUserRepository;
import mindswap.academy.moviereview_api.persistence.repository.user.role.IRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static mindswap.academy.moviereview_api.persistence.model.user.UserPojo.*;
import static mindswap.academy.moviereview_api.persistence.model.user.role.RolePojo.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    IUserService iUserService;
    @Mock
    IUserRepository iUserRepository;
    @Mock
    IRoleRepository iRoleRepository;

    @BeforeEach
    public void setup() {
        this.iUserService = new UserService(
                iUserRepository,
                new UserConverter(new ModelMapper()),
                iRoleRepository
        );
    }

    @Nested
    class getUser {

        @Test
        void test_getUserById() {
            when(iUserRepository.findById(1L))
                    .thenReturn(Optional.of(USER_EXAMPLE));

            UserDto result = iUserService.getUser(1L);

            assertEquals(USER_DTO_EXAMPLE, result);
        }
    }

    @Test
    void test_addUser() {
        when(iRoleRepository.findById(any()))
                .thenReturn(Optional.ofNullable(ROLE_EXAMPLE));

        when(iUserRepository.save(any()))
                .thenReturn(USER_EXAMPLE);

        UserDto result = iUserService.add(USER_DTO_EXAMPLE);

        assertEquals(USER_DTO_EXAMPLE, result);
    }
}
