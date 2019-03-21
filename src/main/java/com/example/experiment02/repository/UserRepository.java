package com.example.experiment02.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.experiment02.entity.Adress;
import com.example.experiment02.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepository {
    @PersistenceContext
    private EntityManager em;
    //添加用户返回包含用户时间戳的对象
    public User addUser(){
        User user = new User("flj3");
        em.persist(user);
        return user;
    }
    //添加地址并指定地址对应的用户
    public Adress addAdress(){
        Adress adress = new Adress("234");
        adress.setUser(addUser());
        em.persist(adress);
        return adress;
    }
    //更新用户名，merge(),refresh()方法
    public User updaUser(int uid,String name){
        User user1 = new User();
        user1.setId(uid);
        User user2 = em.merge(user1);
        em.refresh(user2);
        user2.setName(name);
        return user2;
    }
    //更新用户名，用find()方法
    public User updaUser2(int uid,String name){

        //查询，返回受管对象，
        User user = em.find(User.class, 1);
        //em.refresh(user);//从数据库更新数据到受管对象，覆盖null数据
        //此处多余，find返回全部参数
        user.setName(name);
        return user;
    }
    /**
     * 尝试使用merge()，以及find()2种方法分别实现
     * 更新指定地址为指定用户
     * @param aid
     * @param uid
     * @return
     */
    public Adress updateAddress(int aid, int uid) {
        //Adress adress = em.find(User.class, aid);
        Adress adress = new Adress();
        adress.setId(aid);
        Adress adress1 = em.merge(adress);
        //em.refresh(adress1);//多余操作不需要从数据库更新数据
        User user1 = new User();
        user1.setId(uid);
        User user2 = em.merge(user1);
        //em.refresh(user2);
        adress1.setUser(user2);

        return adress1;
    }
    //find（）方法，更新指定地址为指定用户
    public Adress updateAddress2(int aid,int uid){
        User user = em.find(User.class, uid);
        Adress adress = em.find(Adress.class, aid);
        adress.setUser(user);
        return adress;
    }
    /**
     * 返回指定用户的全部地址，没有返回空集合，而非null
     * @param uid
     * @return
     */
    public List<Adress> listAddresses(int uid) {
        List<Adress> list= em.find(User.class, uid).getAdresses();
        for(Adress adress:list){
            System.out.println(adress.getDetail());
        }
        return list;
    }
    //删除地址
    public void removeAddress(int aid){
        em.remove(em.find(Adress.class, aid));
    }
    //删除用户，设置级联操作或手动删除相关地址
    public void removeUser(int aid){
        em.remove(em.find(User.class, aid));
    }

}
