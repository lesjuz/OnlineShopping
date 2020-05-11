package edu.miu.onlineshopping.formatter;



import edu.miu.onlineshopping.domain.Role;
import edu.miu.onlineshopping.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


@Component
public class RoleFormatter implements Formatter<Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public String print(Role role, Locale locale) {

        return String.valueOf(role.getId());
    }

    @Override
    public Role parse(String text, Locale locale) throws ParseException {

        System.out.println(roleService.get(Long.parseLong(text)).getRole());
        return roleService.get(Long.parseLong(text));
    }

}

