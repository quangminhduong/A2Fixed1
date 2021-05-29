package shift_manager_pro.controllers.shifts;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import shift_manager_pro.dao.*;
import shift_manager_pro.models.User;
import shift_manager_pro.utils.Views;

import java.util.List;
import java.util.Map;

public class ViewAllShiftsController implements Handler {

    static final String PATH = Views.templatePath("manager/shifts/list.html");

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
    List<User> users = UserDao.INSTANCE.getAll();
    for (User user : users){
    UserDao.INSTANCE.updateWorkingHour(user);}
        Map<String, Object> model = Views.baseModel(ctx);
        model.put("shifts", ShiftDao.INSTANCE.getFromNow());
        model.put("users", users);
        ctx.render(PATH, model);
    }
}
