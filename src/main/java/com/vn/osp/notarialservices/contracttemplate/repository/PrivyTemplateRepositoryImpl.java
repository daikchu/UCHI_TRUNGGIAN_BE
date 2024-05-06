package com.vn.osp.notarialservices.contracttemplate.repository;

import com.vn.osp.notarialservices.contracttemplate.domain.PrivyTemplateBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by TienManh on 8/11/2017.
 */
@Component
public class PrivyTemplateRepositoryImpl implements PrivyTemplateRepositoryCustom  {
    private static final Logger LOG = LoggerFactory.getLogger(PropertyTemplateRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    public PrivyTemplateRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    @Override
    public Optional<List<PrivyTemplateBO>> list() {
        try {
            List<PrivyTemplateBO> list=entityManager.createQuery("select pr from PrivyTemplateBO pr ORDER BY entry_time DESC",PrivyTemplateBO.class).getResultList();
            return Optional.ofNullable(list);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.list()");
            return Optional.ofNullable(new ArrayList<PrivyTemplateBO>());
        }
    }

    @Override
    public Optional<List<PrivyTemplateBO>> listItemPage(int offset, int number) {
        try {
            List<PrivyTemplateBO> list=entityManager.createQuery("select pr from PrivyTemplateBO pr ORDER BY entry_time DESC",
                    PrivyTemplateBO.class).setFirstResult(offset).setMaxResults(number).getResultList();
            return Optional.ofNullable(list);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.listItemPage()");
            return Optional.ofNullable(new ArrayList<PrivyTemplateBO>());
        }
    }
    @Override
    public Optional<List<PrivyTemplateBO>> listItemPageByType(String type, int offset, int number) {
        try {
            List<PrivyTemplateBO> list=entityManager.createQuery("select pr from PrivyTemplateBO pr where pr.type=:type ORDER BY entry_time DESC",
                    PrivyTemplateBO.class).setParameter("type", type).setFirstResult(offset).setMaxResults(number).getResultList();
            return Optional.ofNullable(list);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.listItemPage()");
            return Optional.ofNullable(new ArrayList<PrivyTemplateBO>());
        }
    }

    @Override
    public Optional<List<PrivyTemplateBO>> getByType(String type) {
        try {
            List<PrivyTemplateBO> list=entityManager.createQuery("select pr from PrivyTemplateBO pr where pr.type = :type ORDER BY entry_time",
                    PrivyTemplateBO.class).setParameter("type", type).getResultList();
            return Optional.ofNullable(list);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.getByType()");
            return Optional.ofNullable(new ArrayList<PrivyTemplateBO>());
        }
    }

    @Override
    public Optional<Long> countByType(String type) {
        try {
            long count=(long)entityManager.createQuery("select count(pr.id) from PrivyTemplateBO pr where pr.type = :type").setParameter("type", type).getSingleResult();
            return Optional.ofNullable(count);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.countByType()");
            return Optional.ofNullable(null);
        }
    }

    @Override
    public Optional<Long> total() {
        try {
            long count=(long)entityManager.createQuery("select count(pr.id) from PrivyTemplateBO pr").getSingleResult();
            return Optional.ofNullable(count);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.countByType()");
            return Optional.ofNullable(null);
        }
    }

    @Override
    public Optional<PrivyTemplateBO> getById(int id) {
        try {
//            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("vpcc_npo_privy_property_get_by_type");
//            storedProcedure
//                    .setParameter("id", id);
//            storedProcedure.execute();


            PrivyTemplateBO itemadd=new PrivyTemplateBO();

//            PrivyTemplateBO item12=entityManager.find(PrivyTemplateBO.class,6);
//            entityManager.remove(item12);


//            entityManager.merge(itemadd);
//            entityManager.flush();
//            entityManager.merge(itemadd);
//            if(item12!=null){
//                item12.setName("hoho");
//                entityManager.flush();
//            }

//            itemadd.setType((byte)0);
//            itemadd.setName("hoho");
//            itemadd.setUpdate_user(1);
//            itemadd.setEntry_user(1);
//            itemadd.setHtml("test");
//            itemadd.setEntry_time(new Date());
//            itemadd.setUpdate_time(itemadd.getEntry_time());
//            Date date=new Date();
//            System.out.println(itemadd.getEntry_time());
//            System.out.println(date);
//            entityManager.persist(itemadd);
////            entityManager.merge(itemadd);
//            entityManager.flush();
//            System.out.println("gia tri id:"+itemadd.getId());


//            PrivyTemplateBO item12=entityManager.find(PrivyTemplateBO.class,5);
//            item12.setName("manh");
//            entityManager.merge(item12);
//            entityManager.flush();

//            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
//            try {
//
//                entityManager1.getTransaction().begin();
//                System.out.println("bat dau");
//                PrivyTemplateBO item122=entityManager1.find(PrivyTemplateBO.class,5);
//                item122.setName("manh123");
//                entityManager1.merge(item122);
//                // Do something with the EntityManager such as persist(), merge() or remove()
//                entityManager1.getTransaction().commit();
//            } catch(Exception e) {
//                e.printStackTrace();
//                entityManager1.getTransaction().rollback();
//            }

            PrivyTemplateBO item=entityManager.find(PrivyTemplateBO.class,id);
            return Optional.ofNullable(item);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.getById()");
            return Optional.ofNullable(new PrivyTemplateBO());
        }
    }

    @Override
    public Optional<Boolean> add(PrivyTemplateBO item) {
        try {

            boolean check=true;
            while(check){
                String code=UUID.randomUUID().toString();
                long count=(long)entityManager.createQuery("select count(pr.id) from PrivyTemplateBO pr where pr.code = :code").setParameter("code", code).getSingleResult();
                if(count==0) check=false;
                item.setCode(code);
            }
            entityManager.persist(item);
            entityManager.flush();

            return Optional.of(Boolean.valueOf(true));
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PrivyTemplateRepositoryImpl.add()");
            return Optional.of(Boolean.valueOf(false));
        }
    }

    @Override
    public Optional<Boolean> update(PrivyTemplateBO item) {
        try {
            PrivyTemplateBO itemDB=entityManager.find(PrivyTemplateBO.class,item.getId());
            itemDB.setName(item.getName());
            itemDB.setDescription(item.getDescription());
            itemDB.setHtml(item.getHtml());
            itemDB.setUpdate_user(item.getUpdate_user());
            itemDB.setUpdate_time(item.getUpdate_time());
            itemDB.setDisable(item.isDisable());
            entityManager.merge(itemDB);
            entityManager.flush();
            return Optional.of(Boolean.valueOf(true));
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.update()");
            return Optional.of(Boolean.valueOf(false));
        }
    }

    @Override
    public Boolean deleteItem(int id) {
        try {
            PrivyTemplateBO item=entityManager.find(PrivyTemplateBO.class,id);
            if(item!=null){
                entityManager.remove(item);
            }
            return Boolean.valueOf(true);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm PropertyTemplateRepositoryImpl.delete()");
            return Boolean.valueOf(false);
        }
    }
}
