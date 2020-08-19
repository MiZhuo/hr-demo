package fun.mizhuo.hrserver.service;

import fun.mizhuo.hrserver.mapper.HrMapper;
import fun.mizhuo.hrserver.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: Mizhuo
 * @time: 2020/8/19 10:06 下午
 * @description:
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUserName(username);
        if(hr == null){
            throw new UsernameNotFoundException("用户不存在,请检查!");
        }
        return hr;
    }
}
