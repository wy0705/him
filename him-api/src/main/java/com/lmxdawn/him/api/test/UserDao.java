package com.lmxdawn.him.api.test;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import java.io.IOException;

//import com.yunpan.bean.UserBean;
//import com.yunpan.db.HBaseDB;
//import com.yunpan.util.Constants;
@Repository
public class UserDao {
    //向use_id写数据
    public void add(UserBean userbean) {        
        try {
            //向gid表中添加数据，并且定义id值
            long id = HBaseDB.getId(Constants.HBASE_TABLE_GID, Constants.HBASE_FAMILY_GID_GID, Constants.HBASE_ROW_KEY_GID_GID, Constants.HBASE_COLUMN_GID_GID);
            System.out.println("表gid添加成功");
            Table table;
            //向user_id表中添加数据，并且定义id值
            table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_USER_ID));
            Put put = new Put(Bytes.toBytes(userbean.getName()));
            put.addColumn(Bytes.toBytes(Constants.HBASE_FAMILY_USER_ID_ID), Bytes.toBytes(Constants.HBASE_COLUMN_USER_ID_ID), Bytes.toBytes(id));
            table.put(put);
            table.close();
            System.out.println("表user_id添加成功");
            //向id_user中添加数据
            table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_ID_USER));
            put = new Put(Bytes.toBytes(id));
            put.addColumn(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER), Bytes.toBytes(Constants.HBASE_COLUMN_ID_USER_EMAIL), Bytes.toBytes(userbean.getEmail()));
            put.addColumn(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER), Bytes.toBytes(Constants.HBASE_COLUMN_ID_USER_NAME), Bytes.toBytes(userbean.getName()));
            put.addColumn(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER), Bytes.toBytes(Constants.HBASE_COLUMN_ID_USER_PWD), Bytes.toBytes(userbean.getPwd()));
            table.put(put);
            table.close();
            System.out.println("表id_user添加成功");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } 

    }
    //这种方法返回值，是判断是否登陆成功，但是并没有把用户信息返回回去
    public boolean xxlogin(UserBean userbean) {
        boolean flag = false;
        try {

            Table table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_USER_ID));
            Get get = new Get(Bytes.toBytes(userbean.getName()));

            get.addColumn(Bytes.toBytes(Constants.HBASE_FAMILY_USER_ID_ID), Bytes.toBytes(Constants.HBASE_COLUMN_USER_ID_ID));
            //根据用户名获取ID
            Result result = table.get(get);
            table.close();
            table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_ID_USER));
            get = new Get(result.getValue(Bytes.toBytes(Constants.HBASE_FAMILY_USER_ID_ID), Bytes.toBytes(Constants.HBASE_COLUMN_USER_ID_ID)));

            get.addColumn(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER), Bytes.toBytes(Constants.HBASE_COLUMN_ID_USER_PWD));
            Result result2 = table.get(get);
            table.close();
            String password =Bytes.toString(result2.getValue(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER), Bytes.toBytes(Constants.HBASE_COLUMN_ID_USER_PWD)));
            if(password.equals(userbean.getPwd())){
                flag =  true;
            }
            else {
                flag = false;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }

    public UserBean login(UserBean userBean) {
        //通过username去user_id中匹配id
        try {
            Table table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_USER_ID));
            Get get = new Get(Bytes.toBytes(userBean.getName()));
            //列族，列
            get.addColumn(Bytes.toBytes(Constants.HBASE_FAMILY_USER_ID_ID), Bytes.toBytes(Constants.HBASE_COLUMN_USER_ID_ID));
            Result result = table.get(get);
            System.out.println("获取name对应的id");
            if(result != null){
                long id = Bytes.toLong(result.getValue(Bytes.toBytes(Constants.HBASE_FAMILY_USER_ID_ID), Bytes.toBytes(Constants.HBASE_COLUMN_USER_ID_ID)));

                table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_ID_USER));
                get= new Get(Bytes.toBytes(id));
                get.addFamily(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER));
                Result results = table.get(get);
                UserBean userBean2 = new UserBean();
                userBean2.setId(id);
                userBean2.setEmail(Bytes.toString(results.getValue(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER), Bytes.toBytes(Constants.HBASE_COLUMN_ID_USER_EMAIL))));
                userBean2.setName(Bytes.toString(results.getValue(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER),Bytes.toBytes(Constants.HBASE_COLUMN_ID_USER_NAME))));
                userBean2.setPwd(Bytes.toString(results.getValue(Bytes.toBytes(Constants.HBASE_FAMILY_ID_USER_USER),Bytes.toBytes(Constants.HBASE_COLUMN_ID_USER_PWD))));
                if(userBean.getPwd() != null){
                    if(userBean2.getPwd().equals(userBean.getPwd())){
                        System.out.println("成功登陆");
                        return userBean2;
                    }
                }


            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }
}
