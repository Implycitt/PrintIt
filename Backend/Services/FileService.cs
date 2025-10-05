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
            string filePath = ConstructFilePath(fileName);

            if (CheckFileExists(filePath))
            {
                return false;
            }

            File.Create(filePath);
            return true;
        }
        
        public bool UpdateTypeFileName(string fileName, string newFileName)
        {
            string filePath = ConstructFilePath(fileName);
            string newFilePath = ConstructFilePath(newFileName);

            if (!CheckFileExists(filePath) || CheckFileExists(newFilePath))
            {
                return false; 
            }

            try
            {
                File.Move(filePath, newFilePath);
                DeleteTypeFile(filePath);
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return false;
            }
        }

        public bool DeleteTypeFile(string fileName)
        {

            string filePath = ConstructFilePath(fileName);

            if (!CheckFileExists(filePath))
            {
                return false;
            }

            try
            {
                File.Delete(filePath);
                return true;
            } catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                return false;
            }
        }

        public string ConstructFilePath(string fileName)
        {
            string file = fileName + ".json";
            string filePath = Path.Combine(DirectoryService.baseDirectory, file);
            return filePath;
        }
    }
}
