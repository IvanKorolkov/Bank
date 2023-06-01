package ivan.service;

import ivan.build.Autowired;
import ivan.build.Component;
import ivan.model.Model;
import ivan.model.ModelSQLDAO;

@Component
public class Service {

    @Autowired
    private ModelSQLDAO modelDao;

    public Model getModel(int id) {
        return modelDao.getModel(id);
    }

    public void create(Model model) {
        modelDao.addNewModel(model);
    }

    public void update(Model model) {
        modelDao.updateModel(model);
    }

}
