package DAO;

/* 
 * Classe para tabelas de Muitos para Muitos
 */
public abstract class DAOMTM<T1, T2> 
{
    public abstract void insert(T1 entity1, T2 entity2);

    public abstract void delete(T1 entity1, T2 entity2);

}

