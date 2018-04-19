package site.fish119.adminsadp.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.fish119.adminsadp.domain.sys.Department;
import site.fish119.adminsadp.repository.sys.DepartmentRepository;
import site.fish119.adminsadp.service.BaseService;

import java.util.List;

/**
 * @Project adminsadp
 * @Package site.fish119.adminsadp.service.sys
 * @Author fish119
 * @Date 2018/4/19 10:07
 * @Version V1.0
 */
@Service
public class DepartmentService extends BaseService<Department> {
    private final DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
//        this.userRepository = userRepository;
    }

    public List<Department> findAll(){
        return departmentRepository.findByParentIsNullOrderBySortAsc();
    }

    @Transactional
    public void save(Department department) {
        Department dbDepartment = department.getId() == null ? department : departmentRepository.getOne(department.getId());
        if (dbDepartment.getId() != null) {
            dbDepartment.setName(department.getName());
            dbDepartment.setSort(department.getSort());
        }
        dbDepartment.setParent(department.getPidWithoutParent() == null || department.getPidWithoutParent() == 0 ? null : departmentRepository.getOne(department.getPidWithoutParent()));
        departmentRepository.save(dbDepartment);
    }

    @Transactional()
    public void del(Long id) {
        Department department = departmentRepository.getOne(id);
        if (department.getParent() == null) {
            departmentRepository.deleteById(id);
        } else {
            Department parentDepartment = departmentRepository.getOne(department.getPid());
            department.setParent(null);
            parentDepartment.getChildren().remove(department);
            departmentRepository.delete(department);
        }
    }
}
