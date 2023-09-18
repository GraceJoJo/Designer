package com.jojo.design.module_test.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/4 5:50 PM
 * desc   : 数据库对应的bean类（切记不要把属性声明为private，否则编译会报：Error:Execution failed for task ':module-test:kaptDebugKotlin'）
 */

/**
 * 有些时候, 数据库中的某些域或几组域必须是唯一的. 你可以通过将注解@Index的unique属性设置为true, 强制完成唯一的属性.
 * 下面的代码示例防止表有两行数据在列firstName和lastName拥有相同值:
 * @Entity(tableName = "user",indices = {@Index(value = {"first_name", "last_name"}, unique = true)})
 */

//entity声明定义，并且指定了映射数据表明
@Entity(tableName = "user")
public class User {
    //设置主键，并且定义自增增
    @PrimaryKey(autoGenerate = true)
    //字段映射具体的数据表字段名
    @ColumnInfo(name = "uid")
    int uid;
    //字段映射具体的数据表字段名
    @ColumnInfo(name = "first_name")
    String firstName;
    //字段映射具体的数据表字段名
    @ColumnInfo(name = "last_name")
    String lastName;
    @ColumnInfo(name = "age")
    int age;

    @Ignore
    public User(int uid, String firstName, String lastName, int age) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.age = age;
    }

    //必须指定一个构造方法，room框架需要。并且只能指定一个
//，如果有其他构造方法，则其他的构造方法必须添加@Ignore注解
    public User() {
    }

    //其他构造方法要添加@Ignore注解
    @Ignore
    public User(int uid) {
        this.uid = uid;
    }

    //Setter、Getter方法是需要添加的，为了支持room工作
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}