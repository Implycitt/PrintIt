using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Printit.Backend.Services
{
    internal class FileService
    {
        public bool CheckFileExists(string filePath)
        {
            try
            {
                File.Exists(filePath);
                return true;
            } catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return false;
            }
        }

        public bool CreateTypeFile(string fileName)
        {
            string file = fileName + ".json";
            string filePath = Path.Combine(DirectoryService.baseDirectory, file);
            if (checkFileExists(filePath))
            {
                return false;
            }
            File.Create(filePath);
            return true;
        }
        
        public bool UpdateTypeFileName(string fileName)
        {
            // TODO
        }

        public bool DeleteTypeFile(string fileName)
        {
            // TODO
        }
    }
}
