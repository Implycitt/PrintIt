using Printit.Backend.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Printit.Backend.Services
{
    internal class PrinterService
    {

        public Queue<string> PrinterLabelQueue = new Queue<string>();

        public bool AddLabelToQueue(string labelPath)
        {
            // TODO
        }

        public bool SetPrinterSettings(PrinterSettings settings)
        {
            // TODO
        }

        public bool PrintQueue(PrinterSettings settings)
        {
            // TODO
        }

        public bool PrintSingleLabel(PrinterSettings settings, string labelPath)
        {
            // TODO
        }

        public bool AddToQueue(string labelPath)
        {
            // TODO 
        }
    }
}
