using ClientTest.ServiceProduct;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Metadata.W3cXsd2001;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;

namespace ClientTest
{
    public class Program 
    {
        static void Main(string[] args)
        {
            //Instanciar Clases con los metodos
            FuncionProducts testProducts = new FuncionProducts();
            FuncionCategory testCategory = new FuncionCategory();

            //testCategory.AgregarCategoria();
            testCategory.EditarCategoria();
            //testCategory.EliminarCategoria();
            //testCategory.FiltrarCategoria();
        }



    }
}
