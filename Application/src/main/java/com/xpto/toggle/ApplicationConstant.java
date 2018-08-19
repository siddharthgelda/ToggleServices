package com.xpto.toggle;

public class ApplicationConstant {
    public static String ServiceSql = "insert into services(name,version) values (?,?)";
    public static String toggleSql = "insert into toggle(name,isActivate) value (?,?);";
    public static String createOneToManyRelationshipSql = "insert into tooggleservices(serviceId,toggleId,status) value(?,?,?)";
    public static String getTogglesByServiceNameSql = "select* from  toggle where id IN (select toggleId from tooggleservices where serviceId IN(select id from services where name= ? and version=?))";
    public static String updateServiceToggle = "update tooggleservices set status=? where serviceId=(select id from services where name= ? ) and toggleId =(SELECT id FROM toggle where name=?)";
}
