var endpoint = new WebSocket("ws://localhost:8080/vetweb-auth/endpoint/auth");

endpoint.onopen = function () {
	console.log('CONEXÃO ABERTA JUNTO AO SERVIDOR P/ COMUNICAÇÃO');
}

endpoint.onmessage = function (message) {
	var novoUsuario = JSON.parse(message.data);
	swal('BEM VINDO ' + novoUsuario.username, 'UM NOVO USUÁRIO FOI INCLUÍDO NA VETWEB ' + novoUsuario.email);
}

endpoint.onerror = function (error) {
	console.log('ERRO NA COMUNICAÇÃO COM O SERVIDOR: ' + error);
}

endpoint.onclose = function (close) {
	console.log('CONEXÃO COM O SERVIDOR FINALIZADA: ' + close.code);
}