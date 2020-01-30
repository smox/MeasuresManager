package persistence.dao;

import org.hibernate.criterion.Restrictions;
import persistence.model.ContainerAmountMap;
import persistence.model.Entry;
import persistence.model.Wine;

import java.util.List;


public class ContainerAmountMapDao extends AbstractDao<ContainerAmountMap> {
    public ContainerAmountMapDao() {
        super(ContainerAmountMap.class);
    }


    @Override
    public ContainerAmountMap findById(Long id) {
        var container = String.valueOf(id);
        return findById(container);
    }

    public ContainerAmountMap findById(String container) {
        return (ContainerAmountMap) getCurrentSession().createCriteria(typeParameterClass)
                .add(Restrictions.eq("container", container))
                .uniqueResult();
        /*
        if(list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;*/
    }
}
