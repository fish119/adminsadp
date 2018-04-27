package site.fish119.adminsadp.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import site.fish119.adminsadp.domain.sys.Menu;
import site.fish119.adminsadp.domain.sys.Role;

import java.util.Set;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.utils
 * @Author fish119
 * @Date 2018/4/10 17:56
 * @Version V1.0
 */
public class MainUtil {
    public static Iterable<Menu> cleanChildrenMenu(Iterable<Menu> menus, Set<Role> roles) {
        for (Menu menu : menus) {
            for (Role role : roles) {
                menu.getChildren().removeIf(
                        subMenu -> subMenu.getMRoles().isEmpty() || !subMenu.getMRoles().contains(role)
                );
            }
        }
        return menus;
    }

    private static Sort getSort(String sortColumn, String direction) {
        Sort.Direction dr = Sort.Direction.ASC;
        if (direction != null && direction.equals("DESC")) dr = Sort.Direction.DESC;
        if (sortColumn == null) sortColumn = "id";
        return new Sort(dr, sortColumn);
    }

    public static Pageable getPageRequest(Integer page, Integer size, String sortColumn, String direction) {
        return PageRequest.of(page == null ? 0 : page, size == null || size <= 1 ? 50 : size, getSort(sortColumn, direction));
    }
}
