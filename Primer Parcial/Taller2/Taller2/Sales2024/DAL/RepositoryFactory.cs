﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL
{
    public class RepositoryFactory
    {
        public static IRepository CreateRepository()
        {
            return new EFReposity(new Entities.Sales_DBEntities());
        }
    }
}
