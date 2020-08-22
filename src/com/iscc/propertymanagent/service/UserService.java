package com.iscc.propertymanagent.service;


        import com.iscc.propertymanagent.domain.Cost;
        import com.iscc.propertymanagent.domain.Costtype;
        import com.iscc.propertymanagent.domain.House;
        import com.iscc.propertymanagent.domain.Household;
        import com.iscc.propertymanagent.domain.Staff;
        import com.iscc.propertymanagent.domain.User;

        import java.util.List;
        import java.util.Map;

public interface UserService {
    int addUser(User user);

    User login(String account);

    Household holdlogin(int holdid);

    Staff stafflogin(int staffid);

    List<Map<String, Object>> holdinfoQuery(int holdid);

    Map<String, Object> detailQuery(int holdid);

    //    Type queryTypeById(int id);
    List<Map<String, Object>> queryAllTypeByCondition(Map<String, Object> params ,int typeid);

    List<Map<String, Object>> queryAllTypeByCondition(int houseid ,int typeid);

    int userPasswordedit(Household household);

    List<Costtype> getcostbycostid(String  typename);

    int uesredit(Household household , House house);
    List<Map<String, Object>> holdnoticeQuery(Map<String, Object> params, int holdid, int typename, int timeNum);

    List<Map<String, Object>> holdnoticeQuery(int holdid, int typename, int timeNum);


    List<Costtype> getcostbycostid(int  typeid);





}
