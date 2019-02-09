package de.CloudEx.master.network.commands;

import de.CloudEx.master.manager.WrapperManager;
import de.CloudEx.service.services.cloud.CloudCommand;

public class CreateCommand extends CloudCommand {

    /** create <wrapper/group/user> (p/s) name **/

    @Override
    public void execute(String[] args) {
        if(args[0].equalsIgnoreCase("create")) {
            if(args.length == 3) {
                if(args[1].equalsIgnoreCase("wrapper")) {
                    WrapperManager.getInstance().createNewWrapper(args[2]);
                }

            } else if(args.length == 3) {

            }
        }
    }

    @Override
    public String getUsage() {
        return "Create <wrapper/group/user> (p/s) name -> Erstellt einen neuen Wrapper oder eine Gruppe!";
    }
}
