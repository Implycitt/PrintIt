using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Printit.Backend.Models
{
    internal class Product
    {
        public String name { get; set; }

        public String subCategory { get; set; }
        
        public String fileLocation { get; set; }

        public String productSize { get; set; }
    }
}
