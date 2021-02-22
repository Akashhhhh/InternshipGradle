package ecommerce.dao;

import ecommerce.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
public class ProductDao {

    public static boolean insertProductToDb(Product obj, Connection con)
    {
        boolean f = false;
        //jdbc code..
        try {

            String q = "insert into product(prod_id,prod_name,sell_price,description,type,quantity) values(?,?,?,?,?,?)";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setObject(1,obj.getProdId());
            pstmt.setString(2, obj.getProdName());
            pstmt.setFloat(3,obj.getSellPrice());
            pstmt.setString(4,obj.getDescription());
            pstmt.setString(5,obj.getType());
            pstmt.setInt(6, obj.getQuantity());

            //execute

            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }

    public static boolean deleteProductToDb(String name, Connection con)
     {
        boolean f = false;
        //jdbc code..
        try {


            String q = "delete from product where prod_name=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setString(1,name);



            //execute

            pstmt.executeUpdate();
            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;


    }
    public static boolean updateProductToDb(int qt, String name, Connection con)
    {
        boolean f = false;
        //jdbc code..
        try {


            String q = "update product set quantity=? where prod_name=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            //set the values of parameters
            pstmt.setInt(1,qt);
            pstmt.setString(2,name);

            //execute

            pstmt.executeUpdate();

            f=true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;


    }
}