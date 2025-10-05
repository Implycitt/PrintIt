using Printit.Backend.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.Json;

namespace Printit.Backend.Services
{
    internal class JsonService
    {

        public String? Serialize(Product productObject)
        {
            try
            {
                return JsonSerializer.Serialize(productObject);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return null;
            }
        }
        public Product? Deserialize(String productString)
        {
            try
            {
                return JsonSerializer.Deserialize<Product>(productString);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return null;
            }
        }
    }
}
