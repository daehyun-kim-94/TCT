using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PrjDS2_Dictionary
{
    class DS2_Dictionary
    {
        static void Main(string[] args)
        {
            Dictionary<string, Effort> m = new Dictionary<string, Effort>();

            string line;
            StreamReader file = new StreamReader("DS_Sample2.csv");
            while ((line = file.ReadLine()) != null)
            {
                Effort userEffort = new Effort();
                char[] delimiterChars = { ',' };
                string[] words = line.Split(delimiterChars);

                string key = words[1];

                if (!m.ContainsKey(key))
                {
                    userEffort.setName(words[2]);
                    userEffort.setProjectA(Convert.ToDouble(words[3]));
                    userEffort.setProjectB(Convert.ToDouble(words[4]));
                    userEffort.setProjectC(Convert.ToDouble(words[5]));

                    m.Add(key, userEffort);
                }
                else
                {
                    m[key].addProjectA(Convert.ToDouble(words[3]));
                    m[key].addProjectB(Convert.ToDouble(words[4]));
                    m[key].addProjectC(Convert.ToDouble(words[5]));
                }
            }
            file.Close(); 

            //foreach (KeyValuePair<string, Effort> items in m.OrderBy(x => x.Key))
            foreach (KeyValuePair<string, Effort> items in m)
            {
                double total = items.Value.getProjectA() + items.Value.getProjectB() + items.Value.getProjectC();
                string s = items.Key + "\t" + items.Value.getName() + "\t" + items.Value.getProjectA() + "\t" + items.Value.getProjectB() + "\t" + items.Value.getProjectC() + "\t=>\t" + total;
                Console.WriteLine(s);
            }
        }
    }

    public class Effort
    {
        private String strName;
        private double ProjectA;
        private double ProjectB;
        private double ProjectC;

        public String getName()
        {
            return strName;
        }
        public void setName(String strName)
        {
            this.strName = strName;
        }
        public double getProjectA()
        {
            return ProjectA;
        }
        public void setProjectA(double n)
        {
            ProjectA = n;
        }
        public double getProjectB()
        {
            return ProjectB;
        }
        public void setProjectB(double n)
        {
            ProjectB = n;
        }
        public double getProjectC()
        {
            return ProjectC;
        }
        public void setProjectC(double n)
        {
            ProjectC = n;
        }
        public void addProjectA(double n)
        {
            ProjectA += n;
        }
        public void addProjectB(double n)
        {
            ProjectB += n;
        }
        public void addProjectC(double n)
        {
            ProjectC += n;
        }
    }
}
