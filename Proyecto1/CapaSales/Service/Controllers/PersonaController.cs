﻿using BLL;
using Entities;
using SLC;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Service.Controllers
{
    public class PersonaController : ApiController, IPersonaService
    {
        [HttpPost]
        public Persona Create(Persona persona)
        {
            var personaLogic = new PersonaLogic();
            var createdPersona = personaLogic.Create(persona);
            return createdPersona;
        }

        [HttpDelete]
        public bool Delete(int id)
        {
            var personaLogic = new PersonaLogic();
            var result = personaLogic.Delete(id);
            return result;
        }

        [HttpGet]
        public Persona RetrieveByID(int id)
        {
            var personaLogic = new PersonaLogic();
            var persona = personaLogic.RetrieveByID(id);
            return persona;
        }

        [HttpPut]
        public bool Update(Persona personaToUpdate)
        {
            var personaLogic = new PersonaLogic();
            var result = personaLogic.Update(personaToUpdate);
            return result;
        }
    }
}