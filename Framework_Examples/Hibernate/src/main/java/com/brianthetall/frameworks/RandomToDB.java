package com.brianthetall.frameworks;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.lang.Integer;
import java.lang.String;
import java.security.SecureRandom;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

public class RandomToDB{

    private static SessionFactory factory;
    
    @Entity
	@Table(name="RandomTable")
	public static class DataBean{

	    @Id
		@GeneratedValue
		@Column(name="id")
		private int id;
	    
	    @Column(name="name")
		private String name;

	    @Column(name="intValue")
		private int r_data;

	    @Column(name="doubleValue")
		private double d_data;

	    DataBean(){}

	    public DataBean(String name)throws NullPointerException{
		if(name==null)
		    throw new NullPointerException("Passed Null name to DataBean");
		else this.name=name;
		SecureRandom r=new SecureRandom();
		r_data=r.nextInt();
		d_data=r.nextDouble();
	    }

	    public String toString(){
		return name+" "+r_data+" "+d_data;
	    }
	    
	    public void setName(String s){name=s;}
	    public void setInt(int i){r_data=i;}
	    public void setDouble(double d){d_data=d;}
	    public String getName(){return name;}
	    public int getInt(){return r_data;}
	    public double getDouble(){return d_data;}

	}
    
    public RandomToDB(){
	try{
	    factory = new AnnotationConfiguration().
		configure().
		addPackage("com.brianthetall.frameworks").
		addAnnotatedClass(DataBean.class).
		buildSessionFactory();
	}catch (Throwable ex) { 
	    System.err.println("Failed to create sessionFactory object." + ex);
	    throw new ExceptionInInitializerError(ex); 
	}

    }

    /**
     * Write a bean to the database, return its ID in the DB-Table
     * @param b DataBean to persist in mySQL
     * @return SQL generated, auto-incrementing ID
     */
    public Integer add(DataBean b){
	Session session=factory.openSession();
	Integer id=null;
	Transaction tx=null;
	try{
	    tx=session.beginTransaction();
	    id=(Integer)session.save(b);
	    tx.commit();
	    System.out.println("Added:"+b);
	}catch(HibernateException e){
	    if (tx!=null) tx.rollback();
	    e.printStackTrace(); 
	}finally {
	    session.close(); 
	}
	return id;
    }
    
    /**
     * Update value by ID.
     * @param id Serializable SQL index for object
     * @name new name, null if no change
     * @param i new integer update
     * @param d new double update
     */
    public void update(Integer id,String name,int i,double d){
	Session session = factory.openSession();
	Transaction tx = null;
	try{
	    tx = session.beginTransaction();
	    DataBean bean = (DataBean)session.get(DataBean.class, id);
	    if(name!=null)
		bean.setName(name);
	    bean.setInt(i);
	    bean.setDouble(d);
	    session.update(bean);
	    tx.commit();
	}catch (HibernateException e) {
	    if (tx!=null) tx.rollback();
	    e.printStackTrace(); 
	}finally {
	    session.close(); 
	}
    }
    
    public DataBean get(Integer id){
	Session session=factory.openSession();
	Transaction tx=null;
	DataBean retval=null;
	try{
	    tx=session.beginTransaction();
	    retval=(DataBean)session.get(DataBean.class,id);
	}catch(HibernateException e){
	    if (tx!=null) tx.rollback();
	    e.printStackTrace(); 
	}finally{
	    session.close();
	}
	return retval;
    }

    public void delete(Integer id){
	Session session = factory.openSession();
	Transaction tx = null;
	try{
	    tx = session.beginTransaction();
	    System.out.println("DeleteID="+id);
	    DataBean bean=(DataBean)session.get(DataBean.class,id);
	    session.delete(bean);
	    tx.commit();
	}catch (HibernateException e) {
	    if (tx!=null) tx.rollback();
	    e.printStackTrace(); 
	}finally {
	    session.close(); 
	}
    }

    public static void main(String argv[]){
	if(argv.length==0){
	    System.err.println("Must pass DB entry names");
	    System.exit(1);
	}
	
	RandomToDB db=new RandomToDB();
	for(String s:argv)
	    db.add(new DataBean(s));
	
	
    }

}

/*
public void listEmployees( ){
    Session session = factory.openSession();
    Transaction tx = null;
    try{
	tx = session.beginTransaction();
	List employees = session.createQuery("FROM Employee").list(); 
	for (Iterator iterator = 
		 employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary()); 
	}
	tx.commit();
    }catch (HibernateException e) {
	if (tx!=null) tx.rollback();
	e.printStackTrace(); 
    }finally {
	session.close(); 
    }
}
*/