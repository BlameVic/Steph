# -*- coding: utf-8 -*-
class Steph::TestPlugin
    include Cinch::Plugin
    include Steph::Utils

    command "test"
    def execute(m)
        m.reply "It works!"
    end
end
