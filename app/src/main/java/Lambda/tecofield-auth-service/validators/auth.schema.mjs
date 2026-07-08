import { z } from "zod";

export const registroSchema = z.object({
  email: z.string().email("Debe ser un correo válido"),
  password: z.string().min(6, "La contraseña debe tener al menos 6 caracteres"),
  nombre: z.string().min(2, "El nombre es muy corto")
});

export const loginSchema = z.object({
  email: z.string().email(),
  password: z.string().min(1, "La contraseña es requerida")
});
