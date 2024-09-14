package com.ufes.command;

import com.ufes.view.ManterUsuarioView;

public class SalvarUsuarioCommand implements IUsuarioCommand {
	private ManterUsuarioView view;

	public SalvarUsuarioCommand(ManterUsuarioView view) {
		this.view = view;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
