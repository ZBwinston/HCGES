package org.wang.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.CollectionUtils;
import org.wang.model.Employee;
import org.wang.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDataListener extends AnalysisEventListener<Employee> {

    private EmployeeService employeeService;

    public EmployeeDataListener(EmployeeService employeeService){
        this.employeeService =employeeService;
    }

    private static final int BATCH_COUNT = 5;
    List<Employee> list = new ArrayList<Employee>();

    @Override
    public void invoke(Employee employee, AnalysisContext analysisContext) {
        list.add(employee);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
    }

    private void saveData() {
        if (!CollectionUtils.isEmpty(list)) {
//            employeeService.saveBatch(list);
            employeeService.saveEmployee(list);
        }

    }
}
