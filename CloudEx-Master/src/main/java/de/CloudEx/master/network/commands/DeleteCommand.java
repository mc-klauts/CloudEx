package de.CloudEx.master.network.commands;

import de.CloudEx.master.manager.WrapperManager;
import de.CloudEx.service.services.cloud.CloudCommand;
import de.CloudEx.service.services.logging.Logger;
import de.CloudEx.service.services.logging.level.INFO;

public class DeleteCommand extends CloudCommand {

    /** delete <wrapper/group/user> name **/

    @Override
    public void execute(String[] args) {
        if(args[0].equalsIgnoreCase("delete")) {
            if(args.length == 3) {
                if(args[1].equalsIgnoreCase("wrapper")) {
                    WrapperManager.getInstance().deleteWrapper(args[2]);
                }
            } else {
                new Logger(INFO.class, getUsage());
            }
        }
    }

    @Override
    public String getUsage() {
        return "Delete <wrapper/group/user> name -> Löscht einen Wrapper oder eine Gruppe!";
    }
}
