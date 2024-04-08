package org.carsim.simulation;

import org.carsim.exceptions.UndefinedCommandException;
import org.carsim.model.Car;
import org.carsim.model.SimulationGrid;
import org.carsim.simulation.command.Command;
import org.carsim.simulation.command.MoveForwardCommand;
import org.carsim.simulation.command.TurnLeftCommand;
import org.carsim.simulation.command.TurnRightCommand;
import org.carsim.util.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for executing commands on a car.
 * It maps command characters (F,L,R) to their corresponding Command implementations.
 */
public class CommandExecutor {
    private final Map<Character, Command> commands = new HashMap<>();


    CommandExecutor(SimulationGrid simulationGrid) {
        commands.put(MoveForwardCommand.COMMAND_CHAR, new MoveForwardCommand(simulationGrid));
        commands.put(TurnRightCommand.COMMAND_CHAR, new TurnRightCommand());
        commands.put(TurnLeftCommand.COMMAND_CHAR, new TurnLeftCommand());
    }

    void executeCommand(Car car, char command) {
        Command cmd = commands.get(command);
        if (cmd != null) {
            cmd.execute(car);
        } else {
            throw new UndefinedCommandException(Constants.COMMAND_NOT_SUPPORTED);
        }
    }
}
