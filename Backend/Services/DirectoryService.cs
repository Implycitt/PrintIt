using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Printit.Backend.Services
{
    internal class DirectoryService
    {
        public static string baseDirectory = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.ApplicationData), "LabelData");
        public bool CheckDataDirectory()
        {
            return Directory.Exists(baseDirectory);
        }

        public bool CreateBaseDirectory()
        {
            try
            {
                Directory.CreateDirectory(baseDirectory);
                return true;
            }
            catch (Exception ex)
            {
                Console.Write(ex.Message);
                return false;
            }
        }
    }
}
