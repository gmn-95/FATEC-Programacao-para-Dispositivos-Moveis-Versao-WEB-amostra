function mascaraCampo(item, formato){
    var campo = item.value.length;
    var resultado = formato.substring(0, 1);
    var texto = formato.substring(campo);
    
    if(texto.substring(0, 1) !== resultado){
        item.value += texto.substring(0, 1);
    }
};

/**/
function validaSenha(){
	var senha = document.getElementById("senha").value;
	var confSenha = document.getElementById("confSenha").value;
	
	if(senha != confSenha){
		alert("Confirme a senha corretamente!");
		document.getElementById("confSenha").focus();
		return false;
	}
	return true;
};
/**/
function desabilitaInput() {
	var optionSelect = document.getElementById("tipoPesquisa").value;

    if(optionSelect == "Todos"){
        document.getElementById("valorProcurado").disabled = true;
    }
    else{
        document.getElementById("valorProcurado").disabled = false;
    }
};

function validaContatoSelecionado(){
	if(document.getElementById("tipoPesquisa-contato").value == "Selecione"){
		alert('Por favor, selecione um contato');
		document.getElementById("tipoPesquisa-contato").focus();
		return false;
	}
	else if(document.getElementById("tipoPesquisa-endereco").value == "Selecione"){
		alert('Por favor, selecione um endereço');
		document.getElementById("tipoPesquisa-endereco").focus();
		return false;
	}
};

function validaCampo() {
	if(document.getElementById("valorProcurado").value == "" || document.getElementById("valorProcurado").value == null){
		alert('Por favor, insira o ID a ser procurado!');
		document.getElementById("valorProcurado").focus();
		return false;
	}
};

//restasdasd
function pesquisaCep(valor) {
	//Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
            document.getElementById('logradouro').value='...';
            document.getElementById('bairro').value='...';
            document.getElementById('cidade').value='...';
            document.getElementById('estado').value='...';

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
            limpa_formulário_cep();
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
        limpa_formulário_cep();
    }
};


function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById('logradouro').value=(conteudo.logradouro);
        document.getElementById('bairro').value=(conteudo.bairro);
        document.getElementById('cidade').value=(conteudo.localidade);
        document.getElementById('estado').value=(conteudo.uf);
    } //end if.
    else {
        //CEP não Encontrado.
        limpa_formulário_cep();
        alert("CEP não encontrado.");
    }
};
   

function limpa_formulário_cep() {
        //Limpa valores do formulário de cep.
        document.getElementById('logradouro').value=("");
        document.getElementById('bairro').value=("");
        document.getElementById('cidade').value=("");
        document.getElementById('estado').value=("");
};

function somenteNumero(e){
    navegador = /msie/i.test(navigator.userAgent);
    
    if(navegador){
        var caractere = e.keyCode;
    }
    else{
        var caractere = e.which;
    }
    
    if(caractere > 47 && caractere < 58){
        return true;
    }
    else{
        if(caractere !== 8){
            return false;
        }
        else{
            return true;
        }
    }
};