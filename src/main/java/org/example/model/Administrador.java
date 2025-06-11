package org.example.model;

public class Administrador {
    private final String email;
    private final String senha;

    public Administrador()
    {
        this.email = "admin@admin.com";
        this.senha = "admin123";
    }

    public boolean autenticar(String email, String senha)
    {
        return this.email.equals(email) && this.senha.equals(senha);
    }
}
