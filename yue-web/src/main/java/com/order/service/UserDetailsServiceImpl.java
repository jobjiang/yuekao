package com.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.order.pojo.TbUser;

public class UserDetailsServiceImpl implements UserDetailsService {

	private OrderService orderService;
	
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		TbUser user = orderService.findByRole(username);
		if(user!=null){
			return new User(username, user.getPassword(), authorities);
		}
		return null;
	}

}
