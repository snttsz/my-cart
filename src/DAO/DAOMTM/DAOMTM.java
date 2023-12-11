package DAO.DAOMTM;

/* 
 * Classe abstrata responsável por gerenciar os métodos das tabelas intermediárias entre fruto do relacionamento muitos para muitos entre duas tabelas
 */
public abstract class DAOMTM<T1, T2> 
{
    /* 
     * Método responsável por inserir uma nova linha na tabela muitos para muitos
     */
    public abstract void insert(T1 entity1, T2 entity2);

    /* 
     * Método responsável por deletar uma linha na tabela muitos para muitos
     */
    public abstract void delete(T1 entity1, T2 entity2);

}

