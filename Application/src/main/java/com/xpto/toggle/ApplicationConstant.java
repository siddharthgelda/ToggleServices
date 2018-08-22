package com.xpto.toggle;

public class ApplicationConstant {
    public static String ServiceSql = "insert into services(name,version) values (?,?)";
    public static String toggleSql = "insert into toggle(name,isActivate) value (?,?);";
    public static String createOneToManyRelationshipSql = "insert into tooggleservices(serviceId,toggleId,status) value(?,?,?)";
    public static String getTogglesByServiceNameSql = "SELECT t.id,t.name,status FROM toggle t INNER JOIN tooggleservices t1 ON t.id=t1.toggleId INNER JOIN services s ON t1.serviceId=s.id WHERE s.name=? AND s.version=?";
    public static String updateServiceToggle = "update tooggleservices set status=? where serviceId=(select id from services where name= ? ) and toggleId IN(SELECT id FROM toggle where name=?)";

    public static String getToggleIdSql="SELECT id FROM toggle where name=?";
}
