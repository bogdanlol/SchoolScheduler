

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AfisareInFnctProfesor extends JPanel
{
    /**
	 * 
	 */
//	public String conn(String sql){
//		String a = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Where is your MySQL JDBC Driver?");
//			e.printStackTrace();
//			
//		}
//
//		
//		Connection connection = null;
//
//		try {
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Rankings","bl", "bl");
//			Statement st =connection.createStatement();
//			
//			ResultSet rs = st.executeQuery(sql);
//		
//			while (rs.next()){
//				a =rs.getString(1);
//	//	System.out.println(rs.getString(1));
//			}
//				
//				connection.close();
//		
//		} catch (SQLException e) {
//			System.out.println("Connection Failed! Check output console");
//			e.printStackTrace();
//			
//		}
//		return a;
//	}
	private static final long serialVersionUID = 1L;

	public AfisareInFnctProfesor(String asd)
    {
        Vector<Object> columnNames = new Vector<Object>();
        Vector<Object> data = new Vector<Object>();

        try
        {
            //  Connect to an Access Database

            String driver = "com.mysql.jdbc.Driver";
//            String url = "jdbc:odbc:???";  // if using ODBC Data Source name
          
              
           
            Class.forName( driver );
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/proiectpie","root", "");

            //  Read data from a table

            String sql = "SELECT zi.denZi as Zi , modul.oraModul as Ora,discipline.numeDisc as Disciplina,profesor.nume_prof as Profesor,formatii.fSubgrupa as Subgrupa,program.tipModul as Tip,sali.numeSala"+
                    " from zi , modul,discipline,profesor,formatii,program,sali "+
                    "where zi.codZi=program.codZi "+
                    "and modul.codModul=program.codM "+
                    "and discipline.codDisc=program.codDisc "+
              
                    "and profesor.id_prof=program.codProfesor "+
                    "and profesor.nume_prof="+"'"+asd+"'"+
                    " and formatii.codF=program.codFormatie "+
                    "and sali.codSala=program.codSala";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names

            for (int i = 1; i <= columns; i++)
            {
                columnNames.addElement( md.getColumnName(i) );
            }

            //  Get row data
//            Medie medie=new Medie();
//            Retng retng=new Retng();
//            String creditesimat="select sum(note.nota*materii.credite) from note,materii,student where note.cods=student.cods and materii.codm=note.codm and student.cods="+retng.Student("1");
//    		String	materii = "select sum(materii.credite) from materii,student,note where materii.codm=note.codm and student.cods = note.cods and student.cods="+retng.Student("1");
            while (rs.next())
            {
                Vector<Object> row = new Vector<Object>(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.addElement( rs.getObject(i) );
                }

                data.addElement( row );
                
            }

            rs.close();
            stmt.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }

        //  Create table with database data

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

		JTable table = new JTable( model );
        JScrollPane scrollPane = new JScrollPane( table );
        add( scrollPane );

        JPanel buttonPanel = new JPanel();
        add( buttonPanel, BorderLayout.SOUTH );
    }

    public static void main(String[] args)
    {
        TableFromDatabase frame = new TableFromDatabase();
        frame.setBounds(80, 80, 500, 500);
        frame.setVisible(true);
    }
}