# Trabalho_01_JDBC
Trabalho 01 - JDBC + Interface gráfica de usuário

UML

class Atividades {
  - id: Long
  - tituloAtividade: String
  - dataAtividade: Date
  - descAtividade: String

  + getId(): long
  + setId(id: long): void
  + getTituloAtividade(): String
  + setTituloAtividade(tituloAtividade: String): void
  + getDataAtividade(): Date
  + setDataAtividade(dataAtividade: Date): void
  + getDescAtividade(): String
  + setDescAtividade(descAtividade: String): void
}

História de usuário

Preciso inserir atividades em uma lista de atividades e esta lista deve conter titulo, descricao e data.
Deve ser possivel incluir, editar e excluir atividades.
As atividades devem ser exibidas em formato de tabela contendo colunas com as informações ID, titulo, descricao e data.

