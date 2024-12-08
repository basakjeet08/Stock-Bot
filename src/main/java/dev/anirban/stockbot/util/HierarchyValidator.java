package dev.anirban.stockbot.util;

import dev.anirban.stockbot.entity.Employee;

import java.util.HashMap;

public class HierarchyValidator {

    private static final HashMap<Employee.EmployeeRole, Integer> hierarchy = new HashMap<>();

    static {
        hierarchy.put(Employee.EmployeeRole.OWNER, 1);
        hierarchy.put(Employee.EmployeeRole.MANAGER, 2);
        hierarchy.put(Employee.EmployeeRole.CASHIER, 3);
        hierarchy.put(Employee.EmployeeRole.STAFF, 3);
    }

    public static boolean isHierarchyInvalid(Employee.EmployeeRole requesterRole, Employee.EmployeeRole operatedOnRole) {
        return hierarchy.get(requesterRole) >= hierarchy.get(operatedOnRole);
    }
}