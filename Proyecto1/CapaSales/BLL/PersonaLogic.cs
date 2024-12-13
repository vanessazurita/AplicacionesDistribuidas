﻿using DAL;
using Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BLL
{
    public class PersonaLogic
    {
        public Persona Create(Persona newPersona)
        {
            Persona result = null;
            using (var r = RepositoryFactory.CreateRepository())
            {
                Persona res = r.Retrieve<Persona>(p => p.email == newPersona.email);
                if (res == null)
                {
                    result = r.Create(newPersona);
                }
                else
                {
                    throw new Exception("Persona already exists");
                }
                return result;
            }
        }

        public Persona RetrieveByID(int ID)
        {
            Persona result = null;
            using (var r = RepositoryFactory.CreateRepository())
            {
                result = r.Retrieve<Persona>(p => p.id == ID);
                return result;
            }
        }

        public bool Update(Persona personaToUpdate)
        {
            bool res = false;
            using (var r = RepositoryFactory.CreateRepository())
            {
                Persona temp = r.Retrieve<Persona>(p => p.email == personaToUpdate.email
                    && p.id != personaToUpdate.id);
                if (temp == null)
                {
                    res = r.Update(personaToUpdate);
                }
                return res;
            }
        }

        public bool Delete(int ID)
        {
            bool res = false;
            var persona = RetrieveByID(ID);
            if (persona != null)
            {
                using (var r = RepositoryFactory.CreateRepository())
                {
                    res = r.Delete(persona);
                }
            }
            return res;
        }

        public List<Persona> FilterByRoleID(int roleID)
        {
            List<Persona> result = null;
            using (var r = RepositoryFactory.CreateRepository())
            {
                result = r.Filter<Persona>(p => p.id_rol == roleID);
            }
            return result;
        }

        public Persona Login(string email, string contrasena)
        {
            Persona result = null;
            using (var r = RepositoryFactory.CreateRepository())
            {
                result = r.Retrieve<Persona>(p => p.email == email && p.contrasena == contrasena);
            }
            return result;  // Si la persona existe con esas credenciales, devolverla.
        }
    }
}
